package dev.bltucker.androidtwirp

import com.google.gson.FieldNamingStrategy
import com.squareup.wire.WireField
import java.lang.reflect.Field

class WireAnnotatedFieldNamingStrategy : FieldNamingStrategy {
    override fun translateName(f: Field): String {
        if(!f.isAnnotationPresent(WireField::class.java)){
            return f.name
        }
        val wireFieldAnnotation = f.getAnnotation(WireField::class.java)

        return wireFieldAnnotation?.jsonName?.ifEmpty { f.name } ?: f.name
    }
}