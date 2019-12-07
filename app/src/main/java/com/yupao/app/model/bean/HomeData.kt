package com.yupao.app.model.bean

data class HomeData(
    val banners: List<Banner>,
    val fleamarket_list: List<Fleamarket>,
    val information_list: List<Information>,
    val member_notice: MemberNotice,
    val resume_list: List<Resume>
) {
    data class Banner(
        val banner: String,
        val id: String,
        val mini_url: String,
        val notice_id: String,
        val order: String,
        val title: String,
        val types: String,
        val url: String
    )

    data class Fleamarket(
        val area_str: String,
        val city_id: String,
        val header_img: String,
        val id: String,
        val image: String,
        val is_check: String,
        val is_end: String,
        val province_id: String,
        val state: String,
        val time: String,
        val time_str: String,
        val title: String,
        val user_name: String
    )

    data class Information(
        val detail: String,
        val id: Int,
        val image: String,
        val is_check: Int,
        val is_end: Int,
        val location: String,
        val occupations: List<String>,
        val show_address: String,
        val sort_time: Int,
        val time_str: String,
        val title: String,
        val top: Int,
        val user_id: Int,
        val user_name: String,
        val user_uuid: Int,
        val uuid: String
    )

    data class MemberNotice(
        val hasNoticeMsg: Int
    )

    data class Resume(
        val area_array: List<String>,
        val area_str: String,
        val authentication: Int,
        val birthday: String,
        val certificate: Int,
        val check: String,
        val city: String,
        val country: String,
        val distance: String,
        val experience: String,
        val gender: String,
        val header_img: String,
        val headerimg: String,
        val id: String,
        val introduce: String,
        val is_end: Int,
        val location: Any,
        val nation: String,
        val occupations: List<String>,
        val occupations_search: String,
        val prof_degree: String,
        val province: String,
        val show_address: String,
        val sort_flag: Int,
        val tags: List<Tag>,
        val time: String,
        val time_str: String,
        val title: String,
        val type: String,
        val update_time: Int,
        val user_id: String,
        val user_name: String,
        val user_uuid: String,
        val username: String,
        val uuid: String
    ) {
        data class Tag(
            val id: String,
            val label_name: String,
            val label_py: String
        )
    }

    override fun toString(): String {
        return "HomeData(banners=$banners, fleamarket_list=$fleamarket_list, information_list=$information_list, member_notice=$member_notice, resume_list=$resume_list)"
    }
}