# VPN HTTPS Relay - Android

Android VPN client for HTTPS relay via Google Apps Script backend.

## Requirements

- Android Studio Hedgehog+
- JDK 17
- Gradle 8.2+

## Build
```bash
./gradlew assembleDebug

## Run

Open in Android Studio and run on device.

## Structure

- VpnService → creates Android VPN interface
- RelayClient → communicates with Google Apps Script
- ProxyServer → handles packet bridge


---

# ✅ ساختار نهایی که باید در GitHub باشد

VPNHTTPSRelay-Android/
│
├── app/
├── gradle/
│   └── wrapper/
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
│
├── gradlew
├── gradlew.bat
├── build.gradle
├── settings.gradle
├── README.md


---

# ✅ جمع‌بندی

| وضعیت فعلی | مناسب GitHub؟ |
|------------|---------------|
| بدون wrapper | ❌ خیر |
| با wrapper | ✅ کاملاً استاندارد |

---

اگر بخواهی، می‌توانم:

- ✅ یک GitHub-ready structure دقیق و بهینه CI-ready برایت طراحی کنم  
- ✅ فایل GitHub Actions برای build خودکار APK بنویسم  
- ✅ نسخه Production-ready تمیزتر برای انتشار عمومی آماده کنم  

بگو هدفت انتشار عمومی است یا فقط توسعه شخصی؟