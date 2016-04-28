package com.ulti.oracle

import com.ulti.oracle.Review
import groovy.transform.Field
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import java.util.regex.Matcher
import java.util.regex.Pattern

@Field CliBuilder cl = new CliBuilder(usage: "java -jar probe-deploy.jar")

// Required parameters
cl.d(argName:'date',   longOpt:'date',      args:1, required:true,  'Date to check if you have a review')


def main() {
    def opt = cl.parse(args)
    validateArgs(opt)
    checkReview(opt)
}

def validateArgs(OptionAccessor opt) {
    def dateFormat = /^(?:(?:31(\/|-|\.)(?:0?[13578]|1[02]))\1|(?:(?:29|30)(\/|-|\.)(?:0?[1,3-9]|1[0-2])\2))(?:(?:1[6-9]|[2-9]\d)?\d{2})$|^(?:29(\/|-|\.)0?2\3(?:(?:(?:1[6-9]|[2-9]\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\d|2[0-8])(\/|-|\.)(?:(?:0?[1-9])|(?:1[0-2]))\4(?:(?:1[6-9]|[2-9]\d)?\d{2})$/

    if (!opt) {
        usage()
        System.exit(1)
    }
    def matcher = ((opt.getProperty('d') ==~ dateFormat))
    if (!matcher){
        println "Month entered does not match expected input. Please enter a number between 01 and 12"
        usage()
        System.exit(1)
    }

}

def checkReview(OptionAccessor opt){
    Review review = new Review(opt.getProperty('d'))
    if (review.fail) {
        usage()
    }
}

def usage() {
    println("""

+ ------------ +
| Description: |
+ ------------ +

To find out if you have a review in a given month for a given year. Enter the numeric number of the month
usage:
    -d mm/dd/yyyy

""")
}

main()