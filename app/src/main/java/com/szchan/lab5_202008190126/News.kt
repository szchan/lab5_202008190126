package com.szchan.lab5_202008190126

class News(val title:String, val content:String)

operator fun String.times(n:Int) = this.repeat(n)