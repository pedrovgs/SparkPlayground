package com.github.pedrovgs.sparkplayground.exercise5

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
case class User(name: Name)

case class Name(title: String, first: String, last: String)
