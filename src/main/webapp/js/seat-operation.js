/**
 * 使用二维数组，保存座位图，默认大小为4*10
 */
var seatMap = [
    ['a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'],
    ['a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'],
    ['a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'],
    ['a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a']
];

/**
 * 根据行数和列数初始化座位图的大小
 * @param rowNum    行数
 * @param columnNum 列数
 * @param seats     座位图的json数组
 *
 "seatMap":[
 {
   "venueSeatId":{
     "row":10,
     "column":10
   },
   "hasSeat":true
 }
 ]
 */
function initSeatMap(rowNum, columnNum, seats) {
    seatMap = new Array(rowNum);
    $.each(seatMap, function (index, val) {
        seatMap[index] = new Array(columnNum);
        for (var i = 0; i < columnNum; i++) {
            seatMap[index][i] = "";
        }
    });

    $.each(seats, function (index, seat) {
        var seat_row = seat.venueSeatId.row;
        var seat_column = seat.venueSeatId.column;
        seatMap[seat_row - 1][seat_column - 1] = seat.hasSeat ? 'a' : '_';
    });
}

/**
 * 保存座位信息
 * @type {{a: {classes: string}}}
 */
var seatInfo = {
    a: {
        //Custom CSS classes which should be applied to seats.
        //'seat-red seat-big' or ['seat-red', 'seat-big']
        //点击座位，转换为空位
        click: seat2space,
        classes: 'default-seat'
    }
};

/**
 * 座位图例
 * @type {{node: *|jQuery|HTMLElement, items: *[]}}
 */
var legendInfo = {
    //jQuery reference to a DIV element where legend should be rendered.
    node: $('#legend-box'),
    //An array of legend item details. Each array element should be a three-element array: [ character, status, description ]
    items: [
        ['a', 'available', '有座位']
    ]
};

/**
 * 添加一行
 */
function add_row() {
    if (seatMap.length === 15) {
        alert("座位行数不可以大于15");
    }
    else {
        //新建一行，长度与当前座位图中每一行的长度相等
        var rowLength = seatMap[0].length;
        var newRow = [];
        for (var i = 0; i < rowLength; i++) {
            newRow.push('a');
        }
        seatMap.push(newRow);
        rerender();
    }
}

/**
 * 增加一列
 */
function add_column() {
    if (seatMap[0].length === 20) {
        alert("座位列数不可以大于20");
    }
    else {
        $.each(seatMap, function (index, val) {
            seatMap[index].push("a");
        });
        rerender();
    }
}

/**
 * 删除一行
 */
function delete_row() {
    if (seatMap.length === 3) {
        alert("座位行数不可以小于3");
    }
    else {
        seatMap.pop();
    }
    rerender();
}

/**
 * 删除一列
 */
function delete_column() {
    if (seatMap[0].length === 10) {
        alert("座位列数不可以小于10");
    }
    else {
        $.each(seatMap, function (index, val) {
            seatMap[index].pop();
        });
        rerender();
    }
}

/**
 * 将座位设置为空
 */
function seat2space() {
    //例如第一排第二列的id为: 1_2
    var thisId = this.node().attr("id");
    var rowAndColumn = thisId.split("_");
    var row = rowAndColumn[0];
    var column = rowAndColumn[1];

    seatMap[row - 1][column - 1] = '_';
    rerender();
}

/**
 * 将空位设置为座位
 */
function space2seat() {
    var row = $(this).parent().prevAll().length + 1;
    var column = $(this).prevAll().length;

    seatMap[row - 1][column - 1] = 'a';

    rerender();
}

/**
 * 将seatMap的行转化为string
 */
function seatMapRow2String() {

    var formatSeatMap = [];

    $.each(seatMap, function (index, val) {
        formatSeatMap.push(val.join(""));
    });

    return formatSeatMap;
}

/**
 * 渲染座位
 */
function renderSeats() {

    $("#seat-map").seatCharts({
        //必须的
        //每个字符表示一个座位，下划线表示没有座位
        //每一行的列数必须相等
        map: seatMapRow2String(),
        seats: seatInfo,
        naming: {
            top: false,
            //默认getId函数
            getId: function (character, row, column) {
                return row + '_' + column;
            },
            //默认getLabel函数
            //Labels will be displayed over seats
            //so if you don't want any labels, just return an empty string.
            getLabel: function (character, row, column) {
                return character;
            }
        },
        //legend
        legend: legendInfo,
        click: function () {
            if (this.status() === 'available') {
                return 'available';
            } else {
                if (this.char() === '_') {
                    alert(this.status() + "\n" + this.node() + "\n" + this.data() + "\n" + this.char());
                }
                // return this.style();
            }
        }
    });
}

/**
 * 重新渲染座位
 */
function rerender() {
    //删除原来的seat-map div
    $("#seat-map").remove();
    //删除座位类型的list
    $(".seatCharts-legendList").remove();
    //添加新的seat-map元素
    var newSeatMap = "<div id='seat-map'></div>";
    //将新的seat-map元素添加到外层container中
    $("#seat-map-container").append(newSeatMap);
    //重新渲染座位图
    renderSeats();
}