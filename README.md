# 🛏️ OrionBed Interview App

This is a Jetpack Compose-based Android demo app built as part of the **OrionBed interview process**.

It simulates a smart bed control interface, allowing primary and secondary users to authenticate, configure their side of the bed, and control temperature settings — all while displaying health stats and routines synced with backend data.

---

## 🚀 Features

- 🔐 Login flow for both primary and secondary users
- 👤 Device selection screen using mock scanned hardware IDs
- 🛌 Side selection UI for bed configuration
- 🌡️ Temperature control and live feedback
- 📊 Stats dashboard (Heart Rate, Sleep, Breathing, etc.)
- 🗓️ Smart schedule cards showing bedtime routines
- 🧪 Uses mocked API responses via Mockoon or Proxyman

---

## 📱 Demo Previews

### 🔐 Login and Setup Flow (secondary user auto-approved)
![Login and Setup](app/media/screen-20250705-233010.gif)

### 🎮 Login and Setup Flow (primary + secondary side setup)
![Bed Control](app/media/screen-20250705-233952.gif)

---

## 🔌 API Endpoints (Mocked)

These APIs were mocked using [Mockoon](https://mockoon.com/) and tested via [Proxyman](https://proxyman.io/).

### `GET /init`

Returns recommended temperature, bedtime, stats, and routine schedules.

#### ✅ Response:
```json
{
  "currentTemperature": 72,
  "recommendedBedtime": "10:30 PM",
  "stats": [
    { "label": "Heart Rate", "value": "62 bpm", "type": "HeartRate" },
    { "label": "Sleep", "value": "7.5 hrs", "type": "Sleep" },
    { "label": "Breathing", "value": "16 rpm", "type": "Breathing" },
    { "label": "Temperature", "value": "72°F", "type": "Temperature" },
    { "label": "Resting Heart Rate", "value": "58 bpm", "type": "HeartRate" },
    { "label": "Deep Sleep Duration", "value": "2.1 hrs", "type": "Sleep" },
    { "label": "Respiratory Rate Variability", "value": "3 rpm", "type": "Breathing" },
    { "label": "Ambient Room Temp", "value": "68°F", "type": "Temperature" },
    { "label": "Skin Conductance", "value": "1.2 µS", "type": "EDA" }
  ],
  "schedules": [
    { "title": "Wind down routine", "time": "9:30 PM", "icon": "wind" },
    { "title": "Sleep time", "time": "10:30 PM", "icon": "moon" },
    { "title": "Wake up", "time": "6:30 AM", "icon": "sun" },
    { "title": "Morning stretch", "time": "6:45 AM", "icon": "stretch" },
    { "title": "Hydration reminder", "time": "7:00 AM", "icon": "water" },
    { "title": "Light breakfast", "time": "7:30 AM", "icon": "breakfast" },
    { "title": "Bed auto-cool", "time": "9:00 PM", "icon": "cool" }
  ]
}

POST /auth
Handles user login. Depending on the credentials, returns which flow the user is in.

✅ Request:
{
  "hardwareId": "123-XYZ",
  "username": "rohit",
  "password": "test"
}

🔁 Response:
Returns one of the following strings:
"PRIMARY_SETUP"
"SECONDARY_APPROVED"
"SECONDARY_PENDING"
"SECONDARY_DENIED"
"ERROR"

POST /temperature
Sets the target bed temperature.

✅ Request:
{
  "hardwareId": "123-XYZ",
  "temperature": 70
}
🔁 Response:
204 No Content or 200 OK with no body

🧱 Tech Stack
💡 Jetpack Compose for declarative UI

🔐 Hilt for dependency injection

🧪 Mockoon and Proxyman for API mocking/testing

🚀 Retrofit + Moshi for networking and serialization

🧠 MVVM architecture with ViewModels and side-effect handling

🎨 Custom Compose styling with gradients and Material 3


app/
├── login/                # Auth flow UI + ViewModels
├── home/                 # Temperature control screen
├── schedules/            # Bedtime routine cards
├── stats/                # Stat grid with dynamic tiles
├── network/              # API service + request/response DTOs
├── di/                   # Hilt module setup
└── ui/theme/             # Gradient, typography, and color setup


To test the app without a real backend:

Install and open Mockoon

Set up the above 3 endpoints with the expected responses

Run Mockoon on port 3000

In NetworkModule.kt, update the base URL:

On Android Emulator:
kotlin
Copy
Edit
private const val BASE_URL = "http://10.0.2.2:3000/"
On Physical Device:
kotlin
Copy
Edit
private const val BASE_URL = "http://<your-local-ip>:3000/"


📌 Purpose
This app was created as a take-home challenge for the OrionBed interview process to demonstrate:

Clean architecture and separation of concerns

Ability to mock and test APIs independently

Smooth onboarding flows for different user types

Focus on UI/UX consistency and responsiveness






