# 🧳 TravelBuddy

**TravelBuddy** is an innovative mobile travel platform that bridges travelers and local guides. It enables travelers to plan trips and request guidance from local experts, while empowering locals to monetize their knowledge by offering travel guidance services in their regions.

## 🎯 Project Goal

TravelBuddy aims to revolutionize travel experiences by creating a direct connection between explorers seeking authentic, localized travel advice and experienced local guides. The app eliminates the need for expensive travel agencies while promoting cultural exchange and supporting local communities through meaningful travel guidance.

## 👥 User Roles

### Travelers

- Search for destinations
- Describe the type of experience they want
- Receive guidance from local experts
- Direct communication with guides via the app

### Local Guides

- Register and add locations where they offer guidance services
- Browse travel requests matching their expertise
- Respond to traveler inquiries with personalized advice
- Build a profile of destinations they specialize in

---

## 🏗️ Architecture Overview

TravelBuddy follows a **clean architecture** pattern with clear separation of concerns:

- Interface adapters layer: graphical interface, data sources, connection to backend (Firebase DataConnect).
- Domain layer: domain classes

### Firebase DataConnect

TravelBuddy uses **Firebase DataConnect** as a GraphQL API layer on top of PostgreSQL:

**Features:**
- Type-safe GraphQL operations
- Automatic code generation for Kotlin
- Built-in authentication and authorization
- Real-time subscriptions support

---

## 🎨 UI/UX Features

### Navigation Structure
```
LauncherFragment
├─ WelcomeFragment (New/Returning Users)
└─ MainLayoutFragment (Authenticated Users)
   ├─ TripFragment (Home)
   │  ├─ CreateTripFragment
   │  └─ TripAnswersFragment
   └─ GuideSitesFragment (Guide Mode)
      ├─ AddSiteFragment
      └─ RequestGuideFragment
```

### Responsive Design
- Landscape layout (`layout-land/`) with NavigationRailView for tablets
- Portrait layout with BottomNavigationView
- Dynamic padding for system bars and notches

---

## 🌍 Internationalization (i18n)

Multi-language support in:
- `values/strings.xml` (English)
- `values-es/strings.xml` (Spanish)
- `values-fr/strings.xml` (French)

All UI strings are externalized for easy translation.

---

## 🚀 Development Setup

### Prerequisites
- **Android Studio** 2023.1 or newer
- **Android SDK** 34+
- **Kotlin** 1.9+
- **Firebase Account** with DataConnect enabled
- **PostgreSQL Database** (Cloud SQL instance)

### Clone & Configure
```bash
# Clone repository
git clone https://github.com/yourusername/TravelBuddy.git
cd TravelBuddy

# Configure Firebase
firebase login
firebase init

# Update Firebase project ID in backend/.firebaserc
```

### Build & Run
```bash
# Build Android app
./gradlew assembleDebug

# Deploy backend
cd backend
firebase deploy --only dataconnect
```

---

## 🎓 Architecture Patterns

1. **MVVM (Model-View-ViewModel)**
   - Clear separation between UI and business logic
   - Reactive data binding via StateFlow

2. **Repository Pattern**
   - Abstracts data sources
   - Single source of truth
   - Easy testing with mock implementations

3. **Dependency Injection**
   - Loose coupling between components
   - Easy testing and maintenance

4. **Clean Architecture**
   - Domain layer: Business rules (framework-independent)
   - Data layer: Data sources and persistence
   - Presentation layer: UI and user interaction

---

## 🔄 Future Enhancements

- Real-time messaging between guides and travelers
- Ratings and reviews system
- Payment integration for guide services
- Advanced trip recommendations
- Offline mode support
- Push notifications
- Social features (following guides, trip sharing)
- Video/photo uploads for locations

---

## 👨‍💻 Contributors

- **Matteo Sissa**
- **Nicolas Descoteaux**

Developed for DADM (Design and Development of Mobile Applications) 2025

---

## 📧 Support

For questions or issues, please refer to the project repository or contact the development team.

---

**Made with ❤️ for travel enthusiasts and local guides worldwide** 🌍✈️