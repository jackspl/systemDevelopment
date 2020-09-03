const PieChart = function(ctx) {
  // 1、绘制工具
  this.ctx = ctx || document.querySelector("canvas").getContext("2d");
  // 2、绘制饼状图的中心
  this.w = this.ctx.canvas.width;
  this.h = this.ctx.canvas.height;
  // 圆心
  this.x0 = this.w / 2 + 20;
  this.y0 = this.h / 2;
  // 半径
  this.radius = 150;
  // 确定伸出去的线的长度
  this.outLine = 20;
  this.str = "核心监控";
};
PieChart.prototype.init = function(data) {
  // 1、准备数据
  this.restructure();
  this.drawPie(data);
  this.textPie();
};
// 绘制遮挡矩形+中心文字
PieChart.prototype.textPie = function() {
  var that = this;
  that.ctx.font = "20px Microsoft YaHei";
  that.ctx.textAlign = "center";
  that.ctx.textBaseline = "middle";
  that.ctx.beginPath();
  that.ctx.moveTo(340, 170);
  that.ctx.lineTo(340, 220);
  that.ctx.lineTo(460, 220);
  that.ctx.lineTo(460, 170);
  // 闭合
  that.ctx.closePath();
  // 2、填充
  that.ctx.fillStyle = "#02356f";
  that.ctx.fill()
  that.ctx.beginPath()
  that.ctx.fillStyle = 'white';
  that.ctx.fillText(that.str, that.x0, that.y0);
};
// 绘制饼图
PieChart.prototype.drawPie = function(data) {
  var that = this;
  // 1、转化弧度
  var angliList = this.transformAngle(data);
  // 2、绘制饼图
  var startAngle = 0;
  angliList.forEach((item, i)=> {
    var endAngle = startAngle + item.angle;
    that.ctx.fillStyle = item.color
    // 绘制标题
    that.drawTitle(startAngle, item.angle, item.color, item.title);
    startAngle = endAngle;
  });
};
// 伸出去的线 + 圆
PieChart.prototype.drawTitle = function(startAngle, angle, color, title) {
  // 斜边
  var edge = this.radius + this.outLine;
  // X轴方向直角边
  var edgeX = Math.cos(startAngle + angle / 2) * edge;
  // Y轴方向直角边
  var edgeY = Math.sin(startAngle + angle / 2) * edge;
  // 计算出去的点坐标
  var outX = this.x0 + edgeX;
  var outY = this.y0 + edgeY;
  this.ctx.beginPath();
  this.ctx.moveTo(this.x0, this.y0);
  this.ctx.lineTo(outX, outY);
  this.ctx.strokeStyle = "#eee";
  this.ctx.stroke();
  this.ctx.font = "16px Microsoft YaHei";
  // 线的方向怎么判断
  if (outX > this.x0) {
    // 在右边
    this.ctx.beginPath();
    this.ctx.arc(outX + 15, outY + 10, 10, 0, 2 * Math.PI);
    this.ctx.fill();
    this.ctx.textAlign = "left";
  } else {
    // 在左边
    this.ctx.beginPath();
    this.ctx.arc(outX - 35, outY + 10, 10, 0, 2 * Math.PI);
    this.ctx.fill();
    this.ctx.textAlign = "right";
  }
  this.ctx.stroke();
  this.ctx.textBaseline = "bottom";
  this.ctx.fillStyle = "white";
  this.ctx.fillText(title, outX, outY);
};
// 转化弧度的方法
PieChart.prototype.transformAngle = function(data) {
  // 返回的数据内包含弧度
  var total = 0;
  data.forEach(function(item, i) {
    total += item.num;
  });
  // 计算弧度，并追加到当前对象里
  data.forEach(function(item, i) {
    var angle = (item.num / total) * Math.PI * 2;
    item.angle = angle;
  });
  return data;
};
// 随机颜色
PieChart.prototype.getRandomColor = function() {
  var r = Math.floor(Math.random() * 256);
  var g = Math.floor(Math.random() * 256);
  var b = Math.floor(Math.random() * 256);
  return "rgb(" + r + "," + g + "," + b + ")";
};
// 重新绘制
PieChart.prototype.restructure = function () {
  this.ctx.clearRect(0, 0, this.w, this.h)
}

export default PieChart
