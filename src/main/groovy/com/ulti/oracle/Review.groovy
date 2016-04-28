package com.ulti.oracle


import com.mdimension.jchronic.Chronic

/**
 * Created by william on 4/27/2016.
 */
class Review {
    Date date
    Chronic chronic

    Review(date) {
        Date.metaClass.'static'.fromString = { str ->
            com.mdimension.jchronic.Chronic.parse(str).beginCalendar.time
        }
        this.date = Date.fromString(date)
        init()
    }

    def init() {
        def cal = date.toCalendar()
        if (cal.get(Calendar.YEAR) <= 2016){
            println "You dont have a review"
            return
        }
        if ((cal.get(Calendar.MONTH) == 0) && (cal.get(Calendar.YEAR) == 2016)) {
            println "You dont have a review"
            return
        }
        if ((cal.get(Calendar.MONTH) % 2 == 0)) {
            println "You have a review"
        } else {
            println "You dont have a review"
        }
    }
}
