import com.example.sdk.data.model.SummaryItemResponse
import com.example.sdk.data.model.SummaryType
import com.example.sdk.extensions.toMoney

fun summaryDecodeMock(code: String) = SummaryItemResponse(
    "1000".toMoney(),
    SummaryType.PIX,
    "Diogo Kimura Yamashiro",
    code,
    "15/09/2023"
)