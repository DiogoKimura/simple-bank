import com.example.sdk.data.model.TransactionItemResponse
import com.example.sdk.data.model.TransactionListResponse
import com.example.sdk.extensions.toMoney

private val transactionItemMock = TransactionItemResponse(
    title = "12/04/2023",
    subtitle = "Auto posto",
    amount = "100.00".toMoney().formattedTextWithCurrency,
    labelStatus = null,
    descriptionTittle = "Compra realizada às 16:43",
    descriptionSubtitle = "Auto posto Ipiranga marginal tietê",
    descriptionLabel = "Serviços",
    descriptionReceiptId = "1e211e23123012321"
)

val transactionListMock = TransactionListResponse(
    listOf(
        transactionItemMock,
        transactionItemMock,
        transactionItemMock,
        transactionItemMock,
        transactionItemMock,
        transactionItemMock,
        transactionItemMock,
        transactionItemMock,
        transactionItemMock,
        transactionItemMock,
        transactionItemMock
    )
)