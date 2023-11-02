package com.example.sdk.toolkit.scanner

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage

private const val BARCODE_SIZE = 44

class CodeScannerAnalyzer(
    private val supportedBarcodeList: IntArray,
    private val activity: Activity,
    private val listener: ((String?) -> Unit)?
) : ImageAnalysis.Analyzer {

    @SuppressLint("UnsafeOptInUsageError")
    override fun analyze(imageProxy: ImageProxy) {
        if (listener == null) return

        val inputImage =
            InputImage.fromMediaImage(imageProxy.image!!, imageProxy.imageInfo.rotationDegrees)

        // Process image searching for barcodes
        val options = BarcodeScannerOptions.Builder()
            .setBarcodeFormats(supportedBarcodeList.first(), *supportedBarcodeList)
            .build()

        val scanner = BarcodeScanning.getClient(options)
        scanner.process(inputImage)
            .addOnSuccessListener(activity) { barcodes ->
                for (barcode in barcodes) {
                    when (barcode.valueType) {
                        Barcode.FORMAT_ITF -> {
                            if ((barcode.rawValue?.length ?: 0) >= BARCODE_SIZE) {
                                listener.invoke(barcode.rawValue)
                            }
                        }
                        else -> listener.invoke(barcode.rawValue)
                    }
                }
                imageProxy.close()
            }
            .addOnFailureListener { }
            .addOnCompleteListener { imageProxy.close() }
    }
}