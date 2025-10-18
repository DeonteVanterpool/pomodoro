# Noladoro — Minimal Pomodoro

A minimalist Pomodoro timer where each session is tied to a specific task. Built in Kotlin with Jetpack Compose — simple, focused, and labeled so you always know what you're working on. Built with Material UI and for AMOLED displays.

## Quick pitch

- Add a task, tap it, and start a Pomodoro session labeled with that task.

- Default Pomodoro length: 50 minutes.

- Minimal UI — no distractions, just tasks and a timer.

Built for Android with Kotlin + Jetpack Compose, with Hilt Dependency Injection.

## Features

- Add / edit / remove tasks.

- Start a Pomodoro that shows the task name as the session label.

- Minimal, distraction-free interface.

## Getting started (developer)

### Requirements

Android Studio (with Kotlin + Compose support)

JDK 11+ (or the JDK your project Gradle wrapper expects)

Gradle (the project includes a wrapper)

Build & run

#### from the project root
./gradlew assembleDebug
#### or open project in Android Studio and run on emulator / device

## Architecture (high level)

Kotlin + Jetpack Compose UI

ViewModel(s) for state

In-memory Storage 

## Future Expansion

- Edit / Delete tasks

- Persistent storage for tasks (DataStore / Room)

- Notification when a timer completes

- Estimate the number of pomodoros for a task, and increment the counter when a pomodoro is finished

- Export/import session history (CSV)

## Contributing

Open to PRs that keep the UI simple. Please be descriptive if you can with your PRs.

License

MIT — feel free to use and adapt.
