package com.eddsteel.views
import org.scalatest.FlatSpec

class MainTest extends FlatSpec {
  "main" should "provide a cool cat" in {
    assert(Main.cat === "😸")
  }
}
