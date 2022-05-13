import React from "react";

// gets menu items from each meal
function AndrewsMenus(props: { props: string[][] }) {
  // [][][0] --> main dish
  // [][][1] --> ingredients
  const lunch = props.props[0];
  const dinner = props.props[1];

  // lunch menu
  const coursesLunch = [];
  const ingredientsLunch = [];
  const resultLunch = [];

  if (lunch) {
    for (let i = 0; i < lunch.length; i++) {
      if (lunch[i][1].length !== 0) {
        ingredientsLunch.push(lunch[i][1]);
      } else {
        ingredientsLunch.push("No Description Listed!");
      }
    }

    for (let i = 0; i < lunch.length; i++) {
      if (lunch[i][0].length !== 0) {
        coursesLunch.push(lunch[i][0]);
      } else {
        coursesLunch.push("No Course Listed!");
      }
    }
  }

  for (let i = 0; i < coursesLunch.length; i++) {
    resultLunch.push([coursesLunch[i], ingredientsLunch[i]]);
  }

  // dinner menu
  const coursesDinner = [];
  const ingredientsDinner = [];
  const resultDinner = [];

  if (dinner) {
    for (let i = 0; i < dinner.length; i++) {
      if (dinner[i][1].length !== 0) {
        ingredientsDinner.push(dinner[i][1]);
      } else {
        ingredientsDinner.push("No Description Listed!");
      }
    }

    for (let i = 0; i < dinner.length; i++) {
      if (dinner[i][0].length !== 0) {
        coursesDinner.push(dinner[i][0]);
      } else {
        coursesDinner.push("No Course Listed!");
      }
    }
  }

  for (let i = 0; i < coursesDinner.length; i++) {
    resultDinner.push([coursesDinner[i], ingredientsDinner[i]]);
  }

  return (
    <React.Fragment>
      <div className={"page-info"}>
        <h1>Andrews Commons Menu!</h1>
      </div>

      <div className="table-page">
        <div className="title-table">
          <h2>Lunch</h2>
        </div>

        <table className="styled-table">
          <thead>
            <th>Courses</th>
            <th>Description</th>
          </thead>
          <tbody>
            {resultLunch.slice(1, resultLunch.length).map((item, index) => {
              return (
                <tr key={index}>
                  <td key={item[0]}>{item[0]}</td>
                  <td key={item[1]}>{item[1]}</td>
                </tr>
              );
            })}
          </tbody>
        </table>

        <div className="title-table">
          <h2>Dinner</h2>
        </div>

        <table className="styled-table">
          <thead>
            <th>Courses</th>
            <th>Description</th>
          </thead>
          <tbody>
            {resultDinner.slice(1, resultDinner.length).map((item, index) => {
              return (
                <tr key={index}>
                  <td key={item[0]}>{item[0]}</td>
                  <td key={item[1]}>{item[1]}</td>
                </tr>
              );
            })}
          </tbody>
        </table>
      </div>
    </React.Fragment>
  );
}

export default AndrewsMenus;
