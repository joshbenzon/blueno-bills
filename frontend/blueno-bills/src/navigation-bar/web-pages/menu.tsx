import React from "react";
import { useState } from "react";
import { useEffect } from "react";

// ratty and vdub
interface threeMeals {
  breakfast: foodItems[];
  lunch: foodItems[];
  dinner: foodItems[];
}

// andrews and ivy
interface twoMealsOne {
  lunch: foodItems[];
  dinner: foodItems[];
}

// blue room
interface twoMealsTwo {
  breakfast: foodItems[];
  lunch: foodItems[];
}

// jos
interface oneMeal {
  dinner: foodItems[];
}

interface foodItems {
  foods: string[];
}

const Menu = () => {
  // const [menuBreakfast, setMenuBreakfast] = useState<any | null>(null);
  // const [menuLunch, setMenuLunch] = useState<any | null>(null);
  // const [menuDinner, setMenuDinner] = useState<any | null>(null);

  // function setRatty(menu: threeMeals): void {
  //   setMenuBreakfast(menu["breakfast"]);
  //   setMenuLunch(menu["lunch"]);
  //   setMenuDinner(menu["dinner"]);
  // }

  function loadRatty(): void {
    console.log("RATTY");

    fetch("http://localhost:4567/ratty", {
      method: "GET",
    }).then((response) => response.json());
    // .then((response) => console.log(response.json()));
    // .then((menu: threeMeals) => setRatty(menu));
  }

  useEffect(() => {
    loadRatty();

    // console.log("BREAKFAST: " + menuBreakfast);
    // console.log("LUNCH: " + menuLunch);
    // console.log("DINNER: " + menuDinner);
  }, []);

  return (
    <React.Fragment>
      <div className={"page-info"}>
        <h1>Menu Page!</h1>
      </div>

      <div className={"page-buttons"}>
        <div className="ratty-button">
          <button>Sharpe Refectory</button>
        </div>

        <div className="vdub-button">
          <button>Verney Wooley</button>
        </div>

        <div className="andrews-button">
          <button>Andrews Commons</button>
        </div>
      </div>

      <div className={"page-buttons"}>
        <div className="ivy-button">
          <button>Ivy Room</button>
        </div>

        <div className="blue-button">
          <button>Blue Room</button>
        </div>

        <div className="joes-button">
          <button>Josiah's</button>
        </div>
      </div>
    </React.Fragment>
  );
};

export default Menu;
