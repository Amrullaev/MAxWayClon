package com.amrullaev.maxwayclon.utils.extensions

import java.math.BigDecimal


fun Double.getFinanceType(): String{
    val sum = this.toString()
    val strBuilder = StringBuilder()
    val lastIndex = sum.lastIndexOf('.')-1
    var counter = 0
    for (i in lastIndex downTo 0){
        counter++
        strBuilder.append(sum[i])
        if (counter%3==0){
            strBuilder.append(' ')
        }
    }
    return strBuilder.reverse().append(" sum").toString().trim()
}

fun BigDecimal.getFinanceType(): String = "$this sum"