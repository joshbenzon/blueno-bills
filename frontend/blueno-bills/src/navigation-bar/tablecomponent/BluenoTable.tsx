interface Row{
    StudentID: string;
    email:string;
    mealSwipes:string;
    flexPoints:string;
    bearBucks:string;
}

interface TableProp  {
    tableHeaders : string[] | null;
    userEmail :string;
    userMS: number;
    userFP : number;
    userBB: number;
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
              <tr>

                  <td> {props.userEmail}</td>
                  <td> {props.userMS}</td>
                  <td> {props.userFP}</td>
                  <td> {props.userBB}</td>
              </tr>
              </tbody>
          </table>
          
        </div>
      );
}
export default BluenoTable;