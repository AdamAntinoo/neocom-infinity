export class Render {
  public name: string;
  public jsonClass: string = "Render";
  public expanded: boolean = false;

  constructor(values: Object = {}) {
    Object.assign(this, values);
  }
  public toggleExpanded() {
    this.expanded = !this.expanded;
  }

}
