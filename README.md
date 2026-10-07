# NFLTeams v.1 — Setup Instructions (Easier Version)

This folder is now a real, complete Gradle project — `settings.gradle.kts`,
`build.gradle.kts` (root and `app`), and `gradle.properties` are all
already here and already configured (View Binding enabled, all
dependencies declared). You do **not** need to run Android Studio's
"New Project" wizard or copy any files by hand.

## 1. Move the folder out of Desktop first

Before opening it, drag this whole `NFLTeams` folder out of `Desktop`
into a location Android Studio has normal access to — for example:

```
~/AndroidStudioProjects/NFLTeams
```

(Desktop/Documents/Downloads are macOS "protected" folders and can
cause the permission errors you saw before.)

## 2. Open it directly — don't create a new project

In Android Studio: **File → Open**, then select that `NFLTeams` folder
itself (the one containing `settings.gradle.kts`) and click **Open**.

## 3. Let it sync

Android Studio will recognize it as a Gradle project automatically.
One thing it may prompt you about:

- **"Gradle wrapper not found"** (or similar) — this project is missing
  one small binary file (`gradle-wrapper.jar`) that I can't generate
  for you directly. Click whatever Android Studio offers here — usually
  something like **"Use Gradle from: wrapper (create wrapper if
  necessary)"** or a direct **"Create wrapper"** button in the sync
  banner. Android Studio will fetch it itself. This requires your
  Gradle sync to have internet access, which it will anyway (it's also
  downloading the dependencies declared in `app/build.gradle.kts`).

After that, sync should finish normally and the run configuration at
the top should say **"app"** (not "Unnamed").

## 4. Add the team logo

From `logos.zip`, find the Colts logo and drag it into
`app/src/main/res/drawable/`. If it's not named exactly `colts.png` (or
`.webp`), open `fragment_team_detail.xml` and update:

```xml
app:srcCompat="@drawable/colts"
```

to match the actual file name (lowercase, underscores only — rename
the file if needed).

## 5. Run it

Should launch into the Colts' detail screen — logo, name, "AFC South,"
and "Lucas Oil Stadium."

---

## If the auto-wrapper-fix doesn't appear or fails

Some Android Studio versions don't offer that prompt. If so, there's
one more fallback — still easier than manual file copying:

1. File → New → New Project → Empty Views Activity, name it anything,
   save it anywhere temporary (its only job is to donate one file).
2. Once that throwaway project finishes syncing, find
   `gradle/wrapper/gradle-wrapper.jar` inside it.
3. Copy just that one file into `NFLTeams/gradle/wrapper/` (same
   folder as `gradle-wrapper.properties`).
4. Delete the throwaway project, go back to `NFLTeams`, and sync again.

## Design notes

(unchanged from before — see the Kotlin files' comments for the
reasoning behind the fragment/activity split, view binding, and the
`app:srcCompat` vs `tools:srcCompat` logo choice.)
