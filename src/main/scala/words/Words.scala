package words

import scala.collection.immutable.ListMap
import scala.util.matching.Regex

object Words {

  def findAllWordsInText(text: String): Regex.MatchIterator = {

    // Matches all the words (a-z)
    val pattern = "([a-z])\\w+".r

    // Non case-sensitive expression
    pattern.findAllIn(text.toLowerCase)

  }

  def groupWordsByIdentity(words: List[String]): Map[String, Int] = {

    // Groups the words by their identity, retrieving a map.
    // Similar to SQL group.
    words.groupBy(identity).view.mapValues(_.size).toMap

  }

  def orderGroupedWords(groupedWords: Map[String, Int])
                       (lt: ((String, Int), (String, Int)) => Boolean): ListMap[String, Int] = {

    // Sorts the map using a list map (to maintain the order.
    ListMap(groupedWords.toSeq.sortWith(lt): _*)

  }

}
