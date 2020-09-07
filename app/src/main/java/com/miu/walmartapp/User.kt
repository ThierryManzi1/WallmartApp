package com.miu.walmartapp

import java.io.Serializable

class User(val firstName:String, val lastName:String, val email:String, val password:String) : Serializable {
}