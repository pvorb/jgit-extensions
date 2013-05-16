package de.vorb.jgit.ext

import java.util.{ Set => JavaSet }

import scala.collection.JavaConversions.asScalaSet

import org.eclipse.jgit.lib.{ IndexDiff, ObjectId, Repository }
import org.eclipse.jgit.treewalk.FileTreeIterator

/**
 * Provides additional methods for easy interaction with JGit repositories.
 *
 * @param self underlying `Repository` instance
 *
 * @author Paul Vorbach
 */
class RichRepository(val self: Repository) {
  /**
   * Lists all added files since `id`.
   *
   * @param id
   * @return set of added files
   */
  def addedFilesSince(id: ObjectId): List[String] =
    javaSetToScalaList(diff(id).getAdded())

  /**
   * Lists all changed files since `id`.
   *
   * @param id
   * @return set of changed files
   */
  def changedFilesSince(id: ObjectId): List[String] =
    javaSetToScalaList(diff(id).getChanged())

  /**
   * Lists all modified files since `id`.
   *
   * @param id
   * @return set of modified files
   */
  def modifiedFilesSince(id: ObjectId): List[String] =
    javaSetToScalaList(diff(id).getModified())

  /**
   * Lists all removed files since `id`.
   *
   * @param id
   * @return set of removed files
   */
  def removedFilesSince(id: ObjectId): List[String] =
    javaSetToScalaList(diff(id).getRemoved())

  /**
   * Lists all untracked files since `id`.
   *
   * @param id
   * @return set of untracked files
   */
  def untrackedFilesSince(id: ObjectId): List[String] =
    javaSetToScalaList(diff(id).getUntracked())

  def diffFilesSince(id: ObjectId): List[String] = {
    val d = diff(id)
    javaSetToScalaList(d.getAdded(), d.getChanged(), d.getModified(),
      d.getUntracked())
  }

  /**
   * Gets the `IndexDiff` since `id`.
   *
   * @param id
   * @return difference since `id`
   */
  private def diff(id: ObjectId): IndexDiff = {
    val result = new IndexDiff(self, id, new FileTreeIterator(self))
    result.diff() // check if we see any difference
    result
  }

  /**
   * Generates a Scala `List` from one or more Java `Set`s.
   *
   * @param from original set
   * @return equivalent Scala list
   */
  private def javaSetToScalaList[T](first: JavaSet[T],
      more: JavaSet[T]*): List[T] = {
    for (set <- more)
      first.addAll(set)
    asScalaSet(first).toList
  }
}
