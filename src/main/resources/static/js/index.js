// 홈 버튼 이벤트 : 클릭 시 매장/포장 선택 가능한 mainpage 로드
$('#menu').on('click', function() {
   location.replace('./menuManager');
});
$('#join').on('click', function() {
   location.replace('./join');
});
$('#recommend').on('click', function() {
   location.replace('./recommend');
});