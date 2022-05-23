# NYCHighSchoolsLists

### Project overview:
* The app displays a list of schools with a total average SAT score.
* If the user clicks on the school name, it will take the user to ScoreDetailsActivity.
* ScoreDetailsActivity displays detailed scores, admission requirements, and contact information for the school.
* If the user has a network connection, the app will make an asynchronous API request to get a list of schools and a list of scores.
* Once the JSON response is received, the app will update the local database for offline support and display the list of schools in the recycler view.
* If the user doesn't have a network connection, the app won't make network requests and instead it will display a list of schools from the local ROOM database.
* Users can sort schools by school name or by total score

### Libraries used:
* **Kotlin-Coroutine** was used to get school list asynchronusly from network to make responsive UI.

* **Retrofit** was used to send a network request and receive a response.

* For serialization and deserialization, I used the **Gson** converter.

* To make code testable and maintainable, I used the **MVVM architecture components Viewmodel and Livedata** to separate business logic from the user interface.

* For **offline storage**, the MVVM architectural component **ROOM** was used.

* We can get school details from cache memory if there is no network connection.

* Used MVVM architecture complonent- **Data binding** to avoid NullPointerException also to avoid more boilerplate code(eliminates findVieByID())

* **Constraint layout** was used to create a flat view hierarchy and increase rendering performance.

* Used **recyclerview** to show a scrollable list of schools.

### Demo:
<img src="https://user-images.githubusercontent.com/28978071/169885303-87901ed6-858b-45fe-957b-3365366538fb.jpg" width="400" height="790">
<img src="https://user-images.githubusercontent.com/28978071/169885289-bb44cbb4-afff-425e-9fa0-2783536d5e01.jpg" width="400" height="790">
<img src="https://user-images.githubusercontent.com/28978071/169885268-061dd8ed-84b5-45bc-b7fd-2d2a183c8954.jpg" width="400" height="790">



