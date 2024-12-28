package com.karan.apiintegration

data class ResponseModel(
    val `data`: List<Data>? = listOf(),
    val page: Int? = 0,
    val per_page: Int?= 0,
    val support: Support? = Support(),
    val total: Int? = 0,
    val total_pages: Int?=0
) {                   //we use {} for child class
    data class Data(
        val avatar: String?="",
        val emailRM: String?="",
        val first_name: String?="",
        val id: Int? = 0,
        val last_name: String?=""
    )

    data class Support(
        val text: String?="",
        val url: String?=""
    )
}