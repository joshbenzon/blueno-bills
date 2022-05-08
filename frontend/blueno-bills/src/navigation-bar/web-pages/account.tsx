import React from "react";
import BluenoTable from "../tablecomponent/BluenoTable";



interface Row{
  StudentID: string;
  email:string;
  mealSwipes:string;
  flexPoints:string;
  bearBucks:string;
}

interface TableProp{
  tableHeaders: string[]|null;
  rows: Row[]|null;
}

function Account(props: TableProp){
  return (
    <React.Fragment>
      <div className={"page-info"}>
        <h1>Account Page!</h1>
      </div>
      <BluenoTable tableHeaders = {props.tableHeaders} rows = {props.rows}/>
    </React.Fragment>
  );
};

export default Account;
