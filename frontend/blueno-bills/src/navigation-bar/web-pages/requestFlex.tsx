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

function RequestFlex(props: TableProp) {
  return (
    <React.Fragment>
      <div className="page-info">
        <h1>Request: Flex Points</h1>
      </div>
    </React.Fragment>
  );
}

export default RequestFlex;
