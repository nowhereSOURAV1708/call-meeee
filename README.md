Fake Call Android Application

Overview
The Fake Call Android Application is designed to simulate incoming calls with a caller's name and number,
providing users with a way to create realistic fake calling scenarios.
This app can be used to escape uncomfortable situations or as a prank tool.  

Features
- Simulates an incoming call with a customizable caller name and number.
- Plays a ringtone to enhance authenticity.
- Interactive UI with options to accept or reject the fake call.
- Persistent notification to keep the service running in the background.

Getting Started

Prerequisites
- Android Studio installed on your system.
- A connected Android device or emulator running Android API level 21 (Lollipop) or higher.

Installation
1. Clone the repository or download the source code.
2. Open the project in Android Studio.
3. Sync the project with Gradle files.
4. Run the app on a connected device or emulator.

Key Components

1. CallService
   - A foreground service that handles the logic for simulating a fake call.  
   - Creates a persistent notification to keep the service active.  
   - Launches the `FakeCallActivity` to display the fake call screen.

2. FakeCallActivity 
   - The UI for the fake incoming call.  
   - Displays the caller's name and number.  
   - Includes buttons to accept or reject the call.  
   - Plays a ringtone for added realism.

Key Files
- `CallService.kt`: Manages the background service and notifications.  
- `FakeCallActivity.kt`: Handles the fake call UI and user interactions.  
- `res/layout/activity_fake_call.xml`: The layout file for the fake call screen.  
- `res/drawable/`: Contains resources such as icons and GIFs used in the app.  

Usage
1. Launch the app.  
2. Provide the caller's name and number in the appropriate fields.  
3. Trigger the fake call service.  
4. The app will display an incoming call screen with the specified details.  
5. Accept or reject the call as needed.

Customization
- Change Ringtone: Modify the `playRingtone()` method in `FakeCallActivity.kt` to use a custom ringtone.  
- Notification Icon: Replace `R.drawable.green` in `CallService.kt` with your own icon.  
- GIF Animations: Update the GIFs in the `res/drawable` folder and reference them in `activity_fake_call.xml`.  


Future Improvements
- Add support for scheduling fake calls.  
- Provide an option for users to upload custom ringtones.  
- Enhance UI with additional themes and layouts.  

Contributors
- sourav sandilya (https://github.com/nowhereSOURAV1708/)  

Contact
For queries or support, contact [souravsandilya89882@gmail.com].  
