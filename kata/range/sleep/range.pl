sub startend
{
	$m = matches($range, '(.)(\\d+),(\\d+)(.)'); 
    $start = $m[1];
    if ($m[0] eq "(")
    {
    	$start++;
    }
    $end = $m[2];
    if ($m[3] eq ")")
    {
    	$end--;
    }
}

sub startend12
{
	$range = $range1;
	startend();
	$start1 = $start;
	$end1 = $end;
	$range = $range2;
	startend();
	$start2 = $start;
	$end2 = $end;
}

$range0 = $range;
startend12();
$range = $range0;
startend();


if ($fun eq "contains")
{
	if ($point >= $start && $point < $end)
	{
		return 1;
	}
	else
	{
		return 0;
	}
}

if ($fun eq "allPoints")
{
	@ps = @();
	for ($i = $start; $i <= $end; $i++)
	{
		add(@ps, $i, -1);
	}
	return @ps;
}

if ($fun eq "containsRange")
{	
	if ($start2 >= $start1 && $end2 <= $end1)
	{
		return 1;
	}
	else
	{
		return 0;
	}
}

if ($fun eq "overlaps")
{	
	return iff($start2 <= $end1, 1, 0);
}
