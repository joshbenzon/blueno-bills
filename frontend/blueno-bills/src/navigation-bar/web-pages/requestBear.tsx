import React from "react";

interface Row {
  StudentID: string;
  email: string;
  mealSwipes: string;
  flexPoints: string;
  bearBucks: string;
}

interface TableProp {
  tableHeaders: string[] | null;
  rows: Row[] | null;
}

function RequestBear(props: TableProp) {
  return (
    <React.Fragment>
      <div className="page-info">
        <h1>Request: Meal Credits</h1>
      </div>
    </React.Fragment>
  );
}

export default RequestBear;
