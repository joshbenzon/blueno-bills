# Blueno Bills

## How to Run Our App
### IMPORTANT: In order for SQL connections to work, change “version” in the “pom.xml” file to:
- if you have M1 Chip: 3.34.0
- if you don’t have M1 Chip: 3.34.1
### BackEnd
- In the "backend" folder with the "pom.xml" file, type `mvn package`.
- Then, type `./run --gui`. This will run our REPL and also run the web scraper componenet that loads a Chrome Web Page to scrape menus. (This might take awhile to load!)
- then, type in the terminal `load-database ../data/StudentData.sqlite3`. This will load our database of students.

### FrontEnd
- In the "frontend" folder, type `npm start` to load our website in our terminal.
- From there, follow the descriptions on the page and buttons.
- Enjoy!

## Section 1: Introduction
### 1.1 Project Specific Details

**Members and Roles**
- **Josh Benzon (jbenzon):** Main FrontEnd + FrontEnd/BackEnd Integration (Web Sockets, Web Scraping, Authentication)
- **Jillian Dominguez (jdoming8):** SQL Database + FrontEnd/BackEnd Integration (Transfer Requests and Table Visualization)
- **Shivani Mendiratta (smendir1):** Web Sockets
- **Sarah George (sgeorge8):** Web Scraping
- **Guadalupe Cantera (gcantera):** Authentication

**Estimated Total Time:** 50 Hours

### 1.2 Purpose
**What problem does your project try to solve? Specify how you gathered knowledge about the problem (e.g., user interviews, scholarly articles, the news, personal experience, etc.)**
At Brown, many students are sometimes left with unused Meal Credits at the end of the week. Prior to the semester, each student chooses the amount of maximum Meal Credits they want to use for each week (for example, 14 or 20 Meal Credits). Every week (usually early Friday morning), each student’s Meal Credits amount gets reset to their maximum Meal Credits, regardless of the amount of leftover Meal Credits. Furthermore, our project tries to solve the problem of delegating unused Meal Credits to other students who have no Meal Credits left and need one before the Meal Credits get reset. Additionally, we can also transfer Flex Points and Bear Bucks. We gathered knowledge about our problems from both our personal experience, but also other students’ experiences.

**Who does this problem affect? How? Be specific. Is it an occupation, an age group, a nationality, a racial group? A group of your friends? You, personally? It will likely be a combination of categories. (Note: this group may align more or less directly with your intended user.)**
This problem impacts Brown University students, especially underclassmen who have mandatory meal plans. In general, this problem can be applied to more general university and college students who are in a similar meal plan system.
This problem also affects Brown University students off meal-plan who may sometimes wish to use a Brown dining hall and would prefer using a friend’s extra meal swipe over paying in cash. Additionally, the payment type for Bear Bucks is used throughout each grade, and Bear Bucks are most often used for paying for laundry in Brown dorms.

**Why and how does your project help solve this problem? List at least one alternative solution, whether software-based or not. Why not choose that approach? Valid reasons include your specific skill set, relative cost, greater efficiency, etc. Broadly, how will the project add value? (Examples: makes a process more efficient, accurate, and/or reliable; expands access to a good or service; brings people pleasure/fun)**
Our project helps solve this problem of delegating unused meal swipes by finding students who are willing to give or donate their own meal swipes to students who need them the most. We can utilize a “k-nearest neighbors” strategy that sorts students based on different criteria, such as the amount of meal swipes left for each student. This process makes allocating meal swipes more efficient, as it doesn’t let unused meal swipes go to waste.
Alternatively, one other solution to this problem is swiping for someone else’s meal. Meaning, if a friend has no meal swipes, rather than transferring it to them electronically you could physically give them your own Brown ID for them to swipe. This solution is not ideal because it requires you to be physically present when your friend is swiping, and you can’t expect to be with your friend at every meal time. Our project will allow you to just be able to send swipes electronically so you and your friend do not have to be together.

**Why choose to solve this problem over others? You are choosing to spend your limited time on this project, thereby choosing not to work on others. Why? What is your motivation for choosing this project? “General interest” is not a valid reason. Instead, share why this problem interests you. (Examples: personal experiences; the problem affects a lot of people; the problem affects a particularly vulnerable group; no one has worked on this issue before; profitability of this area)**
Choosing the problem over others allows many university students (not just Brown students) to make sure that they are able to get food from different locations that allow meal swipes. Not many people have thought about a solution to delegate unused resources, such as Meal Swipes and Flex Points (which are non transferable between each academic year). From both personal and students’ experiences, many students are not able to get food, because they have no more meal swipes. This is a huge concern to both academic and nutritional health, especially at huge institutions.
Meal plans are quite expensive at Brown, and those who are on it have expressed that it is cheaper to buy food from Thayer than it is to use at Brown dining halls. However, dining halls such as the Ratty, V-Dub, etc are key places for socializing and require a meal swipe for entrance, so those off of meal plan or with not enough swipes would not even be able to enter these dining halls. Therefore, transferring swipes will allow those who cannot afford to be on meal plan access to key social spaces at Brown.

