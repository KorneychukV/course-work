import {Component, Input, OnInit} from '@angular/core';
import {Menu} from '../../classes/Menu';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  constructor() { }
  @Input() menuList: Menu[] = [];

  ngOnInit(): void {
  }

}
