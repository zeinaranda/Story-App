package com.dicoding.picodiploma.storyapptest1.network.model

import com.google.gson.annotations.SerializedName

data class User(

	@field:SerializedName("name")
	var name: String? = null,

	@field:SerializedName("email")
	var email: String? = null,

	@field:SerializedName("userId")
	var userId: String? = null,

	@field:SerializedName("token")
	var token: String? = null
)
