import React from "react";
import BluenoTable from "../tablecomponent/BluenoTable";

interface TableProp {
  tableHeaders: string[] | null;
  userEmail: string;
  userMS: number;
  userFP: number;
  userBB: number;
}

function Account(props: TableProp) {
  return (
    <React.Fragment>
      <div className={"page-info"}>
        <h1>Account Page!</h1>
      </div>

      <div className="table-page">
        <BluenoTable
          tableHeaders={props.tableHeaders}
          userEmail={props.userEmail}
          userMS={props.userMS}
          userFP={props.userFP}
          userBB={props.userBB}
        />
      </div>
    </React.Fragment>
  );
}

export default Account;
