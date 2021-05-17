package com.codergarden.ex4

object School {

  case class Student(name: String, followedCourses: List[Course]) {
    def coursesTotalCost = followedCourses.foldLeft(BigDecimal(0)) { case (cost, course) => cost + course.price }
  }

  case class Teacher(name: String, courses: List[Course])

  case class Course(name: String, price: BigDecimal, maxEnrolled: Int, enrolledStudents: List[Student]) {
    def isOpen: Boolean = maxEnrolled - enrolledStudents.size > 0
  }
}
