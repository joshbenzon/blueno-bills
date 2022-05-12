# Blueno Bills

## How to Run Our App
### IMPORTANT: In order for to SQL connections to work, change “version” in the “pom.xml” file to:
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
- **Jillian Dominguez (jdoming8):** SQL Database + FrontEnd/BackEnd Integration (Trasnfer Requests and Table Visualization)
- **Shivani Mendiratta (smendir1):** Web Sockets
- **Sarah George (sgeorge8):** Web Scraping
- **Guadalupe Cantera (gcantera):** Authentication

**Estimated Total Time:** 50 Hours
