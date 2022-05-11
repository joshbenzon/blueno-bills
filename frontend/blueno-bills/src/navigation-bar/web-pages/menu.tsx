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
  const [menuBreakfast, setMenuBreakfast] = useState<foodItems | null>(null);
  const [menuLunch, setMenuLunch] = useState<foodItems | null>(null);
  const [menuDinner, setMenuDinner] = useState<foodItems | null>(null);

  function setRatty(menu: foodItems[]): void {
    console.log(menu[0] + " BREAKFAST");
    console.log(menu[1] + " LUNCH");
    console.log(menu[2] + " DINNER");

    setMenuBreakfast(menu[0]);
    setMenuLunch(menu[1]);
    setMenuDinner(menu[2]);
  }

  function loadRatty(): void {
    console.log("RATTY");

    fetch("http://localhost:4567/ratty", {
      method: "GET",
    })
      .then((response) => response.json())
      .then((menu: foodItems[]) => setRatty(menu));
  }

  return (
    <React.Fragment>
      <div className={"page-info"}>
        <h1>Menu Page!</h1>
      </div>

      <div className={"page-buttons"}>
        <div className="ratty-button">
          <button onClick={loadRatty}>Sharpe Refectory</button>
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
