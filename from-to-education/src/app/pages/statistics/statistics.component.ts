import {AfterViewInit, Component, OnInit} from '@angular/core';
import {RestService} from '../../services/rest.service';
import {ColDef} from 'ag-grid-community';
import {FormControl, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-statistics',
  templateUrl: './statistics.component.html',
  styleUrls: ['./statistics.component.css']
})
export class StatisticsComponent implements OnInit {
  width = '100%';
  height = '601px';
  form: FormGroup;

  columnDefs = [
    { headerName: 'Фамилия',
      field: 'lastname'},
    { headerName: 'Имя',
      field: 'firstname' },
    { headerName: 'Отчество',
      field: 'thirdname' },
    { headerName: 'Название программы',
      field: 'program_name' },
    { headerName: 'Пробное тестирование',
      field: 'test_amount',
      valueGetter: mergeValueTest
    },
    { headerName: 'Финальное тестирование',
      field: 'final_amount',
      valueGetter: mergeValueFinal}
  ];
  public rowData = [];
  public rowClassRules = {
    'final-test': 'data.final_fail_amount < data.final_amount'
  };
  constructor(private restService: RestService) { }

  ngOnInit(): void {
    this.form =  new FormGroup({
      "lastname": new FormControl(''),
      "firstname": new FormControl(''),
      "thirdname": new FormControl(''),
    });
    this.search();

  }
  search(): void{
    this.restService.post('statistics', {
      "lastname": this.form.get('lastname').value,
      "firstname": this.form.get('firstname').value,
      "thirdname": this.form.get('thirdname').value
    }).subscribe(
      res => {
        console.log(res.literature);
        this.rowData = res.statistic;
      }, error => {
      }
    );
  }
}
var mergeValueTest = function(params) {
  return params.node.data.test_succ_amount + '/' + params.node.data.test_amount;
};

var mergeValueFinal = function(params) {
  return params.node.data.final_fail_amount + '/' + params.node.data.final_amount;
};
