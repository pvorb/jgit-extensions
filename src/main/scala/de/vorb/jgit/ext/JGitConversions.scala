package de.vorb.jgit.ext

import org.eclipse.jgit.lib.Repository

import scala.language.implicitConversions

object JGitConversions {
  implicit def wrapRepository(repo: Repository): RichRepository =
    new RichRepository(repo)
}
