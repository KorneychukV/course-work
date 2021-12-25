import {AfterViewInit, Component, OnInit} from '@angular/core';
import {RestService} from '../../../../services/rest.service';
import {ColDef} from 'ag-grid-community';
import {FormControl, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-statistics',
  templateUrl: './statistics.component.html',
  styleUrls: ['./statistics.component.css']
})
export class StatisticsComponent implements OnInit {
  width = '50%';
  height = '601px';
  form: FormGroup;

  columnDefs = [
    { headerName: 'Логин',
      field: 'username'},

    { headerName: 'Название программы',
      field: 'programName' },
    { headerName: 'Пробное тестирование',
      field: 'testAmount',
      valueGetter: mergeValueTest
    },
    { headerName: 'Финальное тестирование',
      field: 'finalAmount',
      valueGetter: mergeValueFinal}
  ];
  public rowData = [];
  public rowClassRules = {
    'final-test': 'data.final_fail_amount < data.final_amount'
  };
  constructor(private restService: RestService) { }

  ngOnInit(): void {
    this.form =  new FormGroup({
      username: new FormControl('')
    });
    this.search();

  }
  search(): void{
    this.restService.post('prof/statistics', {
      username: this.form.get('username').value,
    }).subscribe(
      res => {
        this.rowData = res.statistics;
      }, error => {
      }
    );
  }
}
var mergeValueTest = function(params) {
  return params.node.data.testSuccAmount + '/' + params.node.data.testAmount;
};

var mergeValueFinal = function(params) {
  return params.node.data.final_fail_amount + '/' + params.node.data.final_amount;
};
