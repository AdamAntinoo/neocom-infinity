export interface IExpandable {
    expanded: boolean;

    isExpanded(): boolean;
    collapse(): boolean;
    expand(): boolean;
    toggleExpanded();
    // getContentsSize(): number;
}
