package com.github.sixCourse

import org.junit.Test

class JunitCase {
    @Test
    void testFunction() {
       ResumeClient resumeClient= new ResumeClient()
        resumeClient.getResumeDetails2()
      println "--phone information is:    "  + resumeClient.getResumeDetails3()
      println "--content-type is:     " + resumeClient.getResumeDetailHeader()
    }
}
