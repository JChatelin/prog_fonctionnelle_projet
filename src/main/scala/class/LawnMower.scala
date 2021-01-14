package `class`

import exception.DonneesIncorrectesException

class LawnMower(var x: Int,var y : Int,var direction : Char, gridSize: (Int, Int)) {

  @throws(classOf[DonneesIncorrectesException])
  def move(letter : Char) = letter match {
    case 'D' | 'G' => direction = changeDirection(letter)
    case 'A' => movingForward()
    case _ => throw new DonneesIncorrectesException("Instructions not recognized, please use the letter : A, G or D !")
  }

  @throws(classOf[DonneesIncorrectesException])
  def changeDirection(dir : Char) : Char =  {
    direction match {
      case 'N' =>
        if(dir.equals('G')){
          'W'
        }
        else {
          'E'
        }
      case 'E' =>
        if(dir.equals('G')){
          'N'
        }
        else {
          'S'
        }
      case 'W' =>
        if(dir.equals('G')){
          'S'
        }
        else {
          'N'
        }
      case 'S' =>
        if(dir.equals('G')){
          'E'
        }
        else {
          'W'
        }
      case _ => throw new DonneesIncorrectesException("Error in the lawn mower direction!")
    }
  }

  @throws(classOf[DonneesIncorrectesException])
  def movingForward()= {
    direction match {
      case 'N' =>
        if(gridSize._2 > y)
          y=y+1
      case 'E' =>
        if(gridSize._1 > x)
          x+=1
      case 'W' =>
        if(x > 0)
          x-=1
      case 'S' =>
        if(y > 0)
          y-=1
      case _ => throw new DonneesIncorrectesException("Error in the lawn mower direction!")
    }
  }

  override def toString: String = {
    "\"point\" : {\n\t\t\t\"x\" : "+x+",\n\t\t\t\"y\" : "+y+"\n\t\t},\n\t\t  \"direction\" : \""+direction+"\"\n\t\t}"
  }

  def moveInstruction(instructions : Array[Char]): String = {
    var outPut : String ="\t{\n\t \"debut\" : { \n\t\t "+toString()+",\n\t\t\"instructions\" : ["+ instructions.map(entry => s""""${entry}"""" ).mkString(",")+"]\n\t},\n"

    for(instruction <- instructions) {
      move(instruction)
    }
    outPut+="\t{\n\t\"fin\" : { \n\t\t "+toString()+"\n\t}"
    outPut
  }
}
