package `class`

import scala.collection.mutable.ArrayBuffer
import exception.DonneesIncorrectesException

class Grid(width: Int, height : Int) {
  val lawnMowerList : ArrayBuffer[LawnMower] = ArrayBuffer[LawnMower]()
  val lawnMowerStringOutPut : ArrayBuffer[String] = ArrayBuffer()

  def createLawnMower(x: Int, y : Int, direction : Char) = {
    val lawnMower: LawnMower = new LawnMower(x,y,direction, (width,height))
    lawnMowerList.append(lawnMower)
  }
  @throws(classOf[DonneesIncorrectesException])
  def listOfInstruction(instructions : String, lawnMowerPosition: Int) = {
    if(lawnMowerPosition<=lawnMowerList.size)
      lawnMowerStringOutPut.append(lawnMowerList.apply(lawnMowerPosition).moveInstruction(instructions.toCharArray))
    else
      throw new DonneesIncorrectesException("Position of the lawnmower out of bound!")
  }

  override def toString: String = {
    var output : String ="\"limite\" : {\n \t\"x\": "+width+",\n\t\"y\": "+height+"\n},\n \"tondeuse\" : [ \n"
    for(lawnMowerOutput <- lawnMowerStringOutPut) {
      output+=lawnMowerOutput+","
    }
    output=output.dropRight(1)
    output+="\n]"
    output
  }

}
