import React from "react";

// gets menu items from each meal
function BlueRoomMenus(props: { props: string[][] }) {
  // [][][0] --> main dish
  // [][][1] --> ingredients
  const breakfast = props.props[0];
  const lunch = props.props[1];

  // breakfast
  const coursesBreakfast = [];
  const ingredientsBreakfast = [];
  const resultBreakfast = [];

  if (breakfast) {
    for (let i = 0; i < breakfast.length; i++) {
      if (breakfast[i][1].length !== 0) {
        ingredientsBreakfast.push(breakfast[i][1]);
      } else {
        ingredientsBreakfast.push("No Description Listed!");
      }
    }

    for (let i = 0; i < breakfast.length; i++) {
      if (breakfast[i][0].length !== 0) {
        coursesBreakfast.push(breakfast[i][0]);
      } else {
        coursesBreakfast.push("No Course Listed!");
      }
    }
  }

  for (let i = 0; i < coursesBreakfast.length; i++) {
    resultBreakfast.push([coursesBreakfast[i], ingredientsBreakfast[i]]);
  }

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

  return (
    <React.Fragment>
      <div className={"page-info"}>
        <h1>Blue Room Menu!</h1>
      </div>

      <div className="table-page">
        <div className="title-table">
          <h2>Breakfast</h2>
        </div>

        <table className="styled-table">
          <thead>
            <th>Courses</th>
            <th>Description</th>
          </thead>
          <tbody>
            {resultBreakfast
              .slice(1, resultBreakfast.length)
              .map((item, index) => {
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
      </div>
    </React.Fragment>
  );
}

export default BlueRoomMenus;
