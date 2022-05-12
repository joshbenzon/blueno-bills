import React from "react";

// gets menu items from each meal
function JosMenus(props: { props: string[][] }) {
  // [][][0] --> main dish
  // [][][1] --> ingredients
  const dinner = props.props[0];

  // dinner menu
  const coursesDinner = [];
  const ingredientsDinner = [];
  const resultDinner = [];

  if (dinner) {
    for (let i = 0; i < dinner.length; i++) {
      if (dinner[i][1].length !== 0) {
        ingredientsDinner.push(dinner[i][1]);
      } else {
        ingredientsDinner.push("No Ingredients Listed!");
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
        <h1>Josiah's Menu!</h1>
      </div>

      <div className="table-page">
        <div className="title-table">
          <h2>Dinner</h2>
        </div>

        <table className="styled-table">
          <thead>
            <th>Courses</th>
            <th>Ingredients</th>
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

export default JosMenus;
