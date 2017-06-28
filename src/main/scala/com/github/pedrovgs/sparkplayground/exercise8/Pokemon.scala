package com.github.pedrovgs.sparkplayground.exercise8

case class Pokemon(id: String,
                   name: String,
                   type1: String,
                   type2: Option[String],
                   total: Int,
                   hp: Int,
                   attack: Int,
                   defense: Int,
                   spAttack: Int,
                   spDefense: Int,
                   speed: Int,
                   generation: Int,
                   legendary: Boolean)
    extends Ordered[Pokemon] {

  override def compare(that: Pokemon): Int =
    if (this.total != that.total) {
      this.total - that.total
    } else {
      this.speed - that.speed
    }
}
