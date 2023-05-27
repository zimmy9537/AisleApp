package com.zimmy.sample.aisleapp.model

data class Profile(
    val approved_time: Double,
    val disapproved_time: Double,
    val general_information: GeneralInformation,
    val has_active_subscription: Boolean,
    val icebreakers: Any,
    val instagram_images: Any,
    val is_facebook_data_fetched: Boolean,
    val last_seen: Any,
    val last_seen_window: String,
    val lat: Double,
    val lng: Double,
    val meetup: Any,
    val online_code: Int,
    val photos: List<Photo>,
    val preferences: List<Preference>,
    val profile_data_list: List<ProfileData>,
    val show_concierge_badge: Boolean,
    val story: Any,
    val user_interests: List<Any>,
    val verification_status: String,
    val work: Work
)