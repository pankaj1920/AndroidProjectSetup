package com.applaunch.appbase.utils_base


import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.lang.reflect.Type


@Throws(JSONException::class)
fun JSONObject.toMap(): Map<String, Any> {
    val map = mutableMapOf<String, Any>()
    val keysItr: Iterator<String> = this.keys()
    while (keysItr.hasNext()) {
        val key = keysItr.next()
        var value: Any = this.get(key)
        when (value) {
            is JSONArray -> value = value.toList()
            is JSONObject -> value = value.toMap()
        }
        map[key] = value
    }
    return map
}

@Throws(JSONException::class)
fun JSONArray.toList(): List<Any> {
    val list = mutableListOf<Any>()
    for (i in 0 until this.length()) {
        var value: Any = this[i]
        when (value) {
            is JSONArray -> value = value.toList()
            is JSONObject -> value = value.toMap()
        }
        list.add(value)
    }
    return list
}

inline fun <reified T> getListFromJSON(json: String): List<T> {
    val typeToken = TypeToken.getParameterized(List::class.java, T::class.java)
    return Gson().fromJson<List<T>>(json, typeToken.type)!!
}


// all GSON util functions
val gSon: Gson by lazy { GsonBuilder().disableHtmlEscaping().create() }

fun Any?.toJson() = gSon.toJson(this)

// Convert a String to an Object
inline fun <reified T> String.toObject(): T {
    val type = typeToken<T>()
    return gSon.fromJson(this, type)
}

inline fun <reified T> typeToken(): Type = object : TypeToken<T>() {}.type

// Convert a Map to an Object
inline fun <reified T> Map<String, Any>.toObject(): T = convert()

// Convert an object to a Map
fun <T> T.toMap(): Map<String, Any> {
    return convert()
}

// Convert an object of type T to type R
inline fun <T, reified R> T.convert(): R {
    return gSon.toJson(this).toObject()
}

inline fun <reified T> toKList(json: JSONArray): List<T> {
    val typeToken = TypeToken.getParameterized(List::class.java, T::class.java)
    return Gson().fromJson<List<T>>(json.toString(), typeToken.type)!!
}


