interface Row{
    StudentID: string;
    email:string;
    mealSwipes:string;
    flexPoints:string;
    bearBucks:string;
}

interface TableProp  {
    tableHeaders : string[] | null;
    rows : Row[] | null;
}

function BluenoTable(props: TableProp){

    const headerContent = []
    //we don't want the studentID column to show up
    if(props.tableHeaders){

        for(let i = 0; i<props.tableHeaders.length; i++){
            if(i!=0){
                headerContent.push(<th>{props.tableHeaders[i]} </th>)
            }
        }
    }

    return (
        
        <div id="table" >
          <table className="styled-table"> 
              <thead>
                  <tr>
                      {headerContent}
                  </tr>
              </thead>
              <tbody>
              {props.rows?.map(row => 
                  <tr key = {props.rows?.indexOf(row)}>
                  <td>{row.email}</td> 
                  <td>{row.mealSwipes}</td>
                  <td>{row.flexPoints}</td>
                  <td>{row.mealSwipes}</td>   
                  </tr>
              )}
              </tbody>
          </table>
          
        </div>
      );
}
export default BluenoTable;