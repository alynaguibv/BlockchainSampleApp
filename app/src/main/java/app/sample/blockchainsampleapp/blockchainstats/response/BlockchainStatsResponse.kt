package app.sample.blockchainsampleapp.blockchainstats.response

data class BlockchainStatsResponse(
    val description: String,
    val name: String,
    val period: String,
    val status: String,
    val unit: String,
    val values: List<ChartPoint>
)