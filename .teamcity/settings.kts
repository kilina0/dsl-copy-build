import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.projectFeatures.buildReportTab
import jetbrains.buildServer.configs.kotlin.v2019_2.vcs.GitVcsRoot

/*
The settings script is an entry point for defining a TeamCity
project hierarchy. The script should contain a single call to the
project() function with a Project instance or an init function as
an argument.

VcsRoots, BuildTypes, Templates, and subprojects can be
registered inside the project using the vcsRoot(), buildType(),
template(), and subProject() methods respectively.

To debug settings scripts in command-line, run the

    mvnDebug org.jetbrains.teamcity:teamcity-configs-maven-plugin:generate

command and attach your debugger to the port 8000.

To debug in IntelliJ Idea, open the 'Maven Projects' tool window (View
-> Tool Windows -> Maven Projects), find the generate task node
(Plugins -> teamcity-configs -> teamcity-configs:generate), the
'Debug' option is available in the context menu for the task.
*/

version = "2021.2"

project {
    description = "Contains all other projects"

    features {
        buildReportTab {
            id = "PROJECT_EXT_1"
            title = "Code Coverage"
            startPage = "coverage.zip!index.html"
        }
    }

    cleanup {
        baseRule {
            preventDependencyCleanup = false
        }
    }

    subProject(SubProjectA)
    subProject(SubProjectB)
    subProject(SshCheck)
}


object SshCheck : Project({
    name = "ssh check"
})


object SubProjectA : Project({
    name = "SubProjectA"

    subProject(SubProjectA_SubProjectAB)
})


object SubProjectA_SubProjectAB : Project({
    name = "SubProjectAB"

    vcsRoot(SubProjectA_SubProjectAB_HttpsGithubComKilina0secReposGit)

    buildType(SubProjectA_SubProjectAB_Build)
})

object SubProjectA_SubProjectAB_Build : BuildType({
    name = "build"

    vcs {
        root(SubProjectA_SubProjectAB_HttpsGithubComKilina0secReposGit)
    }
})

object SubProjectA_SubProjectAB_HttpsGithubComKilina0secReposGit : GitVcsRoot({
    name = "https://github.com/kilina0/sec_repos.git"
    url = "https://github.com/kilina0/sec_repos.git"
    branch = "refs/heads/master"
})


object SubProjectB : Project({
    name = "SubProjectB"

    subProject(SubProjectB_SubProjectBA)
})


object SubProjectB_SubProjectBA : Project({
    name = "SubProjectBA"

    vcsRoot(SubProjectB_SubProjectBA_HttpsGithubComKilina0secReposGit)

    buildType(SubProjectB_SubProjectBA_Build)
})

object SubProjectB_SubProjectBA_Build : BuildType({
    name = "build"

    vcs {
        root(SubProjectB_SubProjectBA_HttpsGithubComKilina0secReposGit)
    }
})

object SubProjectB_SubProjectBA_HttpsGithubComKilina0secReposGit : GitVcsRoot({
    name = "https://github.com/kilina0/sec_repos.git"
    url = "https://github.com/kilina0/sec_repos.git"
    branch = "refs/heads/master"
})
