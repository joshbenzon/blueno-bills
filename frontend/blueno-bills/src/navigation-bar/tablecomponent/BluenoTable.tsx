


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
    if(props.tableHeaders){
        for (const header in props.tableHeaders) {
            headerContent.push(<th>{props.tableHeaders[header]} </th>)
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
                  <td>{row.StudentID}</td> 
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