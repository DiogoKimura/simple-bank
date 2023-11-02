package com.example.sdk.toolkit.scanner

import android.Manifest
import android.content.pm.PackageManager
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import com.google.mlkit.vision.barcode.common.Barcode
import java.util.concurrent.Executors

private const val TAG = "Simple Scanner"

class SimpleScanner(private val activity: AppCompatActivity) {

    private val cameraExecutor = Executors.newSingleThreadExecutor()

    private val permissionContractActivity =
        activity.registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { permissionCallback }

    var permissionCallback: ((Boolean) -> Unit)? = null
    var supportedBarcodeList: Array<Int> = arrayOf(Barcode.FORMAT_QR_CODE, Barcode.FORMAT_ITF)
    var onDetectedBarcodeListener: ((String?) -> Unit)? = null

    private var isListenerCalled: Boolean = false

    fun start(previewView: PreviewView) {
        if (checkPermissions()) {
            isListenerCalled = false
            initiate(previewView)
        } else {
            permissionContractActivity.launch(Manifest.permission.CAMERA)
        }
    }

    fun stop() {
        cameraExecutor.shutdown()
    }

    fun setupListener(listener: (String?) -> Unit) {
        onDetectedBarcodeListener = listener
    }

    private fun checkPermissions() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(activity, it) == PackageManager.PERMISSION_GRANTED
    }

    private fun callbackManager(value: String?) = value?.let {
        if (!isListenerCalled) {
            isListenerCalled = true
            onDetectedBarcodeListener?.invoke(it)
        }
    }

    private fun initiate(previewView: PreviewView) {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(activity)

        cameraProviderFuture.addListener({
            kotlin.runCatching {
                val cameraProvider = cameraProviderFuture.get()

                val preview = Preview.Builder().build().apply {
                    setSurfaceProvider(previewView.surfaceProvider)
                }

                val analyzer = CodeScannerAnalyzer(
                    supportedBarcodeList.toIntArray(),
                    activity
                ) { value ->
                    callbackManager(value)
                }

                val codeAnalyzer = ImageAnalysis.Builder()
                    .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                    .build()
                    .also {
                        it.clearAnalyzer()
                        it.setAnalyzer(cameraExecutor, analyzer)
                    }

                val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

                cameraProvider.unbindAll()

                cameraProvider.bindToLifecycle(
                    activity,
                    cameraSelector,
                    preview,
                    codeAnalyzer
                )
            }.onFailure {
                Log.e(TAG, "Use case binding failed", it)
            }
        }, ContextCompat.getMainExecutor(activity))
    }

    companion object {
        private val REQUIRED_PERMISSIONS = mutableListOf(
            Manifest.permission.CAMERA
        )
    }
}